const axios = require('axios');

async function consultarAPI(idBarragem) {
    const apiUrl = `http://localhost:8082/api/v1/barragens/${idBarragem}/habitantes`;

    try {
        const response = await axios.get(apiUrl);
        return response.data;
    } catch (error) {
        console.error('Erro na consulta à API:', error);
        throw error;
    }
}

module.exports = { consultarAPI };