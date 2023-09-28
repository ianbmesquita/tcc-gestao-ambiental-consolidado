// src/email.js
require ('dotenv').config();
const nodemailer = require('nodemailer');


const emailConfig = {
    host: "smtp.gmail.com",
    port: 587,
    secure: false,
    auth: {
        user: process.env.USER_MAIL,
        pass: process.env.PASSWORD_MAIL
    },
    tls: {
        rejectUnauthorized: false
    }
};

const transporter = nodemailer.createTransport(emailConfig);

function sendEmail(data, emailTo) {
    const mailOptions = {
        from: 'ianmesquita@gmail.com',
        to: emailTo,
        subject: "[EMERGÊNCIA] Alerta de Incidente",
        text: `Atenção, foi identificado um alerta de nível ${data.grauRisco} para incidente ` +
                `do tipo ${data.tipoIncidente.toLowerCase()} na barragem ${data.nomeBarragem}. ` +
                `Por favor, evacuar a área imediatamente atentando-se às recomendações da Defesa Civil.`,
    };

    transporter.sendMail(mailOptions)
    .then(response => {
        console.log("Email enviado com sucesso");
        transporter.close()
    })
    .catch(error => {
        console.log("Ocorreu erro ao enviar o email.")
        console.log(error)
        transporter.close()
    });
}

module.exports = { sendEmail };
