import { useEffect, useState } from "react"
import { Link, useNavigate,useParams} from "react-router-dom"
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap'
import AppNavBar from './AppNavBar'


function UserEdit(props) {
    const emptyItem = { nome: "", nomeMae: "" }
    const [item, setItem] = useState(emptyItem)
    const navigate = useNavigate()
    const params = useParams()

    useEffect(() => {
        async function fetchData() {
            if(params.id !== 'new') {
                const response = await fetch(`/usuarios/${params.id}`)
                const user = await response.json()
                setItem(user)
            }
        }
        fetchData()
    })

    const handleChange = event => {
        console.log(event.target.name, event.target.value)
        const {target: {value, name}} = event
        const newItem = {...item}
        newItem[name] = value
        setItem(newItem)
    }

    const handleSubmit = async event => {
        event.preventDefault()

        await fetch(`/usuarios/${item.id ?? ''}`, {
            method: (item.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item)
        })
        navigate('/usuarios')
    }

    const title = <h2>{item.id ? 'Alterar Usuário' : 'Novo Usuário'}</h2>

    return (
        <div>
            <AppNavBar/>
            <Container>
                {title}
                <Form onSubmit={handleSubmit}>
                    <FormGroup>
                        <Label for="nome">Nome</Label>
                        <Input type="text" name="nome" id="nome" value={item.nome || ''}
                               onChange={handleChange} autoComplete="name"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="nomeMae">Nome da Mãe</Label>
                        <Input type="text" name="nomeMae" id="nomeMae" value={item.nomeMae || ''}
                               onChange={handleChange} autoComplete="name"/>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Confirmar</Button>
                        <Button color="secondary" tag={Link} to="/usuarios">Cancelar</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    )
}

export default UserEdit