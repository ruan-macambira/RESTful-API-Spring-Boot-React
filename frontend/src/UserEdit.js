import { useEffect, useState } from "react"
import { Link, useNavigate,useParams} from "react-router-dom"
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap'
import AppNavBar from './AppNavBar'


function UserEdit(props) {
    const emptyItem = { nome: "", nomeMae: "" }
    const [item, setItem] = useState(emptyItem)
    const [errors, setErrors] = useState({})
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
        const {target: {value, name}} = event

        const newItem = {...item}
        newItem[name] = value
        setItem(newItem)

        const newErrors = {...errors}
        newErrors[name] = null
        setErrors(newErrors)
    }

    const handleSubmit = async event => {
        event.preventDefault()

        const response = await fetch(`/usuarios/${item.id ?? ''}`, {
            method: (item.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item)
        })

        switch(response.status) {
        case 201:
            navigate('/usuarios')
            break
        case 400:
            const body = await response.json()
            setItem(body.usuario)
            setErrors(body.errors)
            break
        }
    }

    const title = <h2>{item.id ? 'Alterar Usuário' : 'Novo Usuário'}</h2>

    return (
        <div>
            <AppNavBar/>
            <Container>
                {title}
                <Form onSubmit={handleSubmit} novalidate={true}>
                    <FormGroup>
                        <Label for="nome">Nome</Label>
                        <Input type="text" name="nome" id="nome" invalid={errors.nome} value={item.nome ?? ''} required={true}
                               onChange={handleChange}/>
                        <div className="invalid-feedback">{errors.nome}</div>
                    </FormGroup>
                    <FormGroup>
                        <Label for="nomeMae">Nome da Mãe</Label>
                        <Input type="text" name="nomeMae" id="nomeMae" invalid={errors.nomeMae} value={item.nomeMae ?? ''} required={true}
                               onChange={handleChange}/>
                        <div className="invalid-feedback">{errors.nomeMae}</div>
                    </FormGroup>
                    <FormGroup>
                        <Label for="cpf">CPF</Label>
                        <Input type="text" pattern="[0-9]" name="cpf" id="cpf" invalid={errors.cpf} value={item.cpf ?? ''} required={true}
                               onChange={handleChange}/>
                        <div className="invalid-feedback">{errors.cpf}</div>
                    </FormGroup>
                    <FormGroup>
                        <Label for="rg">RG</Label>
                        <Input type="text" pattern="[0-9]" name="rg" id="rg" invalid={errors.rg} value={item.rg ?? ''} required={true}
                               onChange={handleChange}/>
                        <div className="invalid-feedback">{errors.rg}</div>
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