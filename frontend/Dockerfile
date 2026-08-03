# 1. Aşama: Angular Projesini Derleme
FROM node:22-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build --configuration=production

FROM nginx:alpine
RUN echo "server { listen 80; location / { root /usr/share/nginx/html; index index.html; try_files \$uri \$uri/ /index.html; } }" > /etc/nginx/conf.d/default.conf
COPY --from=build /app/dist/frontend/browser /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]