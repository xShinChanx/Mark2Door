# Use the official Node.js image as the base
FROM node:16-slim

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json to the working directory
COPY package*.json ./

# Install project dependencies
RUN npm install

# Copy the rest of the application code to the working directory
COPY . .

# Build the React app for production
RUN npm run build

# Install a simple web server to serve the React app
RUN npm install -g serve

# Set the command to start the server on port 8080
CMD ["serve", "-s", "build", "-l", "8080"]

# Expose port 8080 to the outside world
EXPOSE 8080
