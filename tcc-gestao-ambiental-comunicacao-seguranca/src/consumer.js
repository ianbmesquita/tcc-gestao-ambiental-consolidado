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
    const connection = await amqp.connect(rabbitMqConfig);
    const channel = await connection.createChannel();

    await channel.assertQueue('sigam-notificacao-incidentes');

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
