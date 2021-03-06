import { Component } from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css';

import UserList from './UserList'
import UserEdit from './UserEdit'

class App extends Component {
  state = {
    usuarios: []
  }

  async componentDidMount() {
    const response = await fetch('/usuarios')
    const body = await response.json()
    this.setState({usuarios: body._embedded.usuarioList})
  }

  render() {
    return (
      <BrowserRouter>
        <Routes>
          <Route path='/' exact={true} element={<UserList />} />
          <Route path='usuarios' exact={true} element={<UserList />} />
          <Route path='usuarios/:id' element={<UserEdit/>} />
        </Routes>
      </BrowserRouter>
    )
  }
}

export default App;
