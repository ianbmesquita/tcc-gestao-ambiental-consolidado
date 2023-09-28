require ('dotenv').config();

const amqp = require('amqplib');

const { consultarAPI } = require('./residents')
const { sendEmail } = require('./mailer')

const rabbitMqConfig = {
    host: process.env.HOST_QUEUE,
    port: 5672,
    username: process.env.USERNAME_QUEUE,
    password: process.env.PASSWORD_QUEUE
}

async function startConsumer() {
    console.log("AMQ CONF ", rabbitMqConfig);

    var urlConnection = `amqp://${rabbitMqConfig.username}:${rabbitMqConfig.password}@${rabbitMqConfig.hostname}:${rabbitMqConfig.port}`;

    const connection = await amqp.connect(urlConnection).catch(error => { console.log("ERRO DE CON: ", error)});
    console.log("Passo 1 - OK");
    
    const channel = await connection.createChannel();
    console.log("Passo 2 - OK");

    await channel.assertQueue('sigam-notificacao-incidentes');
    console.log("Passo 3 - OK");

    channel.consume('sigam-notificacao-incidentes', async (message) => {
        try {
            const data = JSON.parse(message.content.toString());
            console.log("Dados: ", data);
            const residents = await consultarAPI(data.idBarragem);
            console.log("Residentes: ", residents);

            residents.forEach(resident => {
                sendEmail(data, resident.email);
            });

            channel.ack(message);
        } catch (error) {
            console.error("Erro ao processar a mensagem:", error);

            channel.reject(message, false);
        }
        
    });
}

module.exports = { startConsumer };
