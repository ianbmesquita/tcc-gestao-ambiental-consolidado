import api2 from '@/pages/api/api2';
import { useEffect, useState } from 'react'
import { AiOutlineSave } from 'react-icons/ai'
import Swal from 'sweetalert2';

interface FormData {
    idBarragem: number;
    dataHora: string;
    grauRisco: string;
    alerta: string;
    origem: string;
}

interface Barragem {
    id: number;
    nome: string;
}

interface Risco {
    risco: string;
    valor: string;
}

interface Incidente {
    tipo: string;
    valor: string;
}

interface Origem {
    tipo: string;
    valor: string;
}

interface InfoPage {
    barragens: Barragem[];
    riscos: Risco[];
    incidentes: Incidente[];
    origens: Origem[];
}

interface AvaliacaoFormProps {
    handleOnSubmitFunction: (data: FormData) => void;
    avaliacaoFormData: FormData;
}

export function AvaliacaoForm({ handleOnSubmitFunction, avaliacaoFormData }: AvaliacaoFormProps) {
    const inputClass = "border border-gray-300 rounded-md text-gray-600 h-10 pl-5 pr-10 bg-white focus:outline-none mb-3 w-full lg:w-11/12"
    const selectBoxClass = "border border-gray-300 rounded-md text-gray-600 h-10 pl-5 pr-10 bg-white hover:border-gray-400 focus:outline-none appearance-none mb-3 w-full lg:w-11/12"
    const buttonClass = "bg-emerald-950 transition duration-200 text-white px-4 py-2 rounded hover:bg-emerald-700 font-semibold w-full lg:w-48"

    const [formData, setFormData] = useState<FormData>(avaliacaoFormData);
    
    const [infoPage, setInfoPage] = useState<InfoPage>({
        barragens: [],
        riscos: [],
        incidentes: [],
        origens: []
    })

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSelectChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };


    const handleOnSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        handleOnSubmitFunction(formData);
    };

    async function fetchAvaliacaoDataPage(token: string | null) {
        await api2.get("api/v1/incidentes/info", {
            headers:{
                Authorization:  'Bearer ' + token
            }
        })
        .then(response => {
            if (response.status === 200) {
                setInfoPage(response.data)
            }
        })
        .catch(error => {
            Swal.fire({
                title: 'Erro!',
                text: 'Ocorreu erro ao carregar a tela',
                icon: 'error',
                confirmButtonText: 'Ok'
              })
        })
    }

    useEffect(() => {
        const token = localStorage.getItem('token')
        fetchAvaliacaoDataPage(token)
    }, []);

    return (
        <div>
            <form className="grid grid-cols-1 md:grid-cols-3 gap-4 p-3" onSubmit={handleOnSubmit}>
                <div>
                    <label htmlFor="dataHora" className="block text-gray-700 font-semibold">Data:</label>
                    <input className={inputClass} type="date" name="dataHora" placeholder="Data da avaliação" onChange={handleInputChange}/>
                </div>

                <div>
                    <label htmlFor="idBarragem" className="block text-gray-700 font-semibold">Barragem:</label>
                    <select className={selectBoxClass} name="idBarragem" onChange={handleSelectChange}>
                        <option>Selecione</option>
                        { 
                            infoPage.barragens.map(barragem => {
                                return (
                                    <option key={barragem.id} value={barragem.id}>
                                        {barragem.nome}
                                    </option>
                                )
                            }) 
                        }
                    </select>
                </div>

                <div>
                    <label htmlFor="alerta" className="block text-gray-700 font-semibold">Risco:</label>
                    <select className={selectBoxClass} name="alerta" onChange={handleSelectChange}>
                        <option>Selecione</option>
                        { 
                            infoPage.incidentes.map(incidente => {
                                return (
                                    <option key={incidente.valor} value={incidente.valor}>
                                        {incidente.tipo}
                                    </option>
                                )
                            }) 
                        }
                    </select>
                </div>

                <div>
                    <label htmlFor="grauRisco" className="block text-gray-700 font-semibold">Grau:</label>
                    <select className={selectBoxClass} name="grauRisco" onChange={handleSelectChange}>
                        <option>Selecione</option>
                        { 
                            infoPage.riscos.map(risco => {
                                return (
                                    <option key={risco.valor} value={risco.valor}>
                                        {risco.risco}
                                    </option>
                                )
                            }) 
                        }
                    </select>
                </div>

                <div>
                    <label htmlFor="origem" className="block text-gray-700 font-semibold">Origem:</label>
                    <select className={selectBoxClass} name="origem" onChange={handleSelectChange}>
                        <option>Selecione</option>
                        { 
                            infoPage.origens.map(origem => {
                                return (
                                    <option key={origem.valor} value={origem.valor}>
                                        {origem.tipo}
                                    </option>
                                )
                            }) 
                        }
                    </select>
                </div>

                <br />
                
                <div className="md:col-span-2">
                    <button className={buttonClass}>
                        <span className="flex flex-row items-center justify-center">
                            <AiOutlineSave className="mr-1" size={20} />
                            Salvar
                        </span>
                    </button>
                </div>
            </form>
        </div>
    )
}