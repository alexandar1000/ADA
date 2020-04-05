FROM node:alpine as build-step
WORKDIR /app
COPY package.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:alpine as prod-step
COPY --from=build-step /app/dist/ada-ui /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
