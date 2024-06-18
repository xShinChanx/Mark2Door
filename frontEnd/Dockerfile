FROM node:latest

WORKDIR /app

COPY package*.json yarn.lock ./

RUN npm install  # Use npm to install dependencies

COPY . .

EXPOSE 3000

CMD ["npm", "start"]
