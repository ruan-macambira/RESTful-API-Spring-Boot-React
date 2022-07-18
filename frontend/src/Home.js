import { Component } from "react"
import { Container, Button } from "reactstrap"
import { Link } from "react-router-dom"

import './App.css'
import AppNavBar from './AppNavBar'

class Home extends Component {
    render() {
        return (
            <div>
                <AppNavBar/>
                <Container fluid>
                    <Button color="link"><Link to="/usuarios">Usuários</Link></Button>
                </Container>
            </div>
        )
    }
}

export default Home