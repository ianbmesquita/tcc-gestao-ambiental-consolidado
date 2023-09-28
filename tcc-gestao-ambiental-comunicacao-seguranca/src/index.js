// const amqp = require('amqplib')

// const fila = "sigam-notificacao-incidentes"

// amqp.connect({
//   host: 'localhost',
//   port: 5672,
//   username: 'sigam-usr',
//   password: 'sigam-pwd'
// })
//     .then((conexao) => {
//       conexao.createChannel()
//           .then((canal) => {
//             canal.consume(fila, (mensagem) => {
//               console.log(mensagem.content.toString())
//             })
//           })
//           .catch((erro) => console.log(erro))
//     })
// .catch((erro) => console.log(erro))

const { startConsumer } = require('./consumer')

startConsumer()