# Use a imagem oficial do Node.js como base
FROM node:18

# Crie um diretório de trabalho no contêiner
WORKDIR /app

# Copie os arquivos de dependência
COPY package*.json ./

# Instale as dependências
RUN npm install

# Copie todos os arquivos do seu aplicativo para o diretório de trabalho
COPY . .

# Porta que seu aplicativo Node.js irá expor (ajuste conforme necessário)
EXPOSE 3001

# Comando para iniciar seu aplicativo
CMD ["node", "src/index.js"]
