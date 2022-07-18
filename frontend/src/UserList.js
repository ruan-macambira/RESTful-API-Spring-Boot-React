import React, {Component} from 'react'
import { Button, ButtonGroup, Container, Table } from 'reactstrap'
import AppNavBar from './AppNavBar'
import {Link} from 'react-router-dom'

class UserList extends Component {
    constructor(props) {
        super(props)
        this.state = {users: []}
        this.remove = this.remove.bind(this)
    }

    async componentDidMount() {
        const response = await fetch('/usuarios')
        const body = await response.json()
        this.setState({users: body._embedded.usuarioList})
    }

    async remove(id) {
        await fetch(`usuarios/${id}`, {
            method: 'DELETE', headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            const updatedUsers = [...this.state.users].filter(i => i.id !== id)
            this.setState({users: updatedUsers})
        })
    }

    render() {
        const {users, isLoading} = this.state

        if(isLoading) {
            return <p> Loading... </p>
        }

        const usersList = users.map(({id, nome, nomeMae}) => {
            return <tr key={id}>
                <td>{nome}</td>
                <td>{nomeMae}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={`/usuarios/${id}`}>Editar</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(id)}>Remover</Button>
                    </ButtonGroup>
                </td>
            </tr>
        })

        return (
            <div>
                <AppNavBar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/usuarios/new">Novo Usuário</Button>
                    </div>
                    <h3>Usuários</h3>
                    <Table className="mt-4">
                        <thead>
                            <tr>
                                <th width="30%">Nome</th>
                                <th width="30%">Nome da Mãe</th>
                                <th width="40%">Ações</th>
                            </tr>
                        </thead>
                        <tbody>{usersList}</tbody>
                    </Table>
                </Container>
            </div>
        )
    }
}

export default UserList;