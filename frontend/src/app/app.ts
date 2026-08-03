import { Component, inject, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class AppComponent {
  stats: any = null;
  private readonly http = inject(HttpClient);
  private readonly cdr = inject(ChangeDetectorRef);

  // Analiz türlerine ADMIN eklendi
  analysisType: 'EMAIL' | 'URL' | 'HISTORY' | 'ADMIN' = 'EMAIL';
  emailContent = '';
  email = '';
  analysisResult: any = null;
  isLoading = false;
  errorMessage = '';
  
  // Geçmiş verileri tutacak liste
  historyList: any[] = []; 

  // ÇÖZÜM BURADA: Parametre içerisine 'ADMIN' eklendi
  setAnalysisType(type: 'EMAIL' | 'URL' | 'HISTORY' | 'ADMIN') {
    this.analysisType = type;
    this.emailContent = ''; 
    this.analysisResult = null;
    this.errorMessage = '';

    if (type === 'ADMIN') { 
      this.fetchStats(); 
    }
    
    // Geçmiş sekmesine geçilirse, veritabanından geçmişi çek
    if (type === 'HISTORY') {
      this.fetchHistory();
    }
  }

  fetchStats(): void {
    this.isLoading = true;
    this.http.get<any>('http://localhost:8080/api/analysis/stats').subscribe({
      next: (data) => {
        this.stats = data;
        this.isLoading = false;
        this.cdr.detectChanges();
      },
      error: () => {
        this.isLoading = false;
        this.errorMessage = 'İstatistikler yüklenemedi.';
        this.cdr.detectChanges();
      }
    });
  }

  fetchHistory(): void {
    const userEmail = this.email.trim();
    if (!userEmail) {
      this.errorMessage = 'Geçmişinizi görmek için lütfen e-posta adresinizi girin.';
      return;
    }

    this.isLoading = true;
    this.http.get<any[]>(`http://localhost:8080/api/analysis/history?email=${userEmail}`)
      .subscribe({
        next: (data) => {
          this.historyList = data;
          this.isLoading = false;
          this.cdr.detectChanges();
        },
        error: (error) => {
          this.isLoading = false;
          this.errorMessage = 'Geçmiş analizler yüklenirken bir hata oluştu veya kullanıcı bulunamadı.';
          this.cdr.detectChanges();
        }
      });
  }

  analyzeContent(): void {
    const content = this.emailContent.trim();
    const userEmail = this.email.trim();

    if (!userEmail || !content) {
      this.errorMessage = this.analysisType === 'EMAIL' 
        ? 'E-posta adresi ve analiz metni boş bırakılamaz.' 
        : 'E-posta adresi ve URL boş bırakılamaz.';
      return;
    }

    this.isLoading = true;
    this.errorMessage = '';
    this.analysisResult = null;
    this.cdr.detectChanges();

    this.http.post<any>('http://localhost:8080/api/analysis/save', {
      email: userEmail,
      contentType: this.analysisType,
      content
    }).subscribe({
      next: (response) => {
        this.isLoading = false; 

        try {
          let riskLevel = response?.riskLevel ?? 'BILINMIYOR';
          let reasons = response?.riskReasons ?? [];

          if (typeof reasons === 'string') {
            try {
              reasons = JSON.parse(reasons);
            } catch (e) {
              reasons = [reasons];
            }
          }

          if (!Array.isArray(reasons)) {
            reasons = reasons ? [reasons] : [];
          }

          reasons = reasons.map((r: any) => {
            if (typeof r === 'object' && r !== null) {
              return r.description || r.text || r.reason || JSON.stringify(r);
            }
            return r;
          });

          this.analysisResult = {
            riskLevel: riskLevel,
            riskReasons: reasons
          };
        } catch (parseError) {
          this.errorMessage = "Sunucudan gelen veri işlenirken hata oluştu.";
        }

        this.cdr.detectChanges();
      },
      error: (error) => {
        this.isLoading = false;
        const body = error.error;
        this.errorMessage = typeof body === 'string'
          ? body
          : body?.message ?? body?.error ?? 'Analiz sırasında bir hata oluştu.';
        this.cdr.detectChanges();
      }
    });
  }
}