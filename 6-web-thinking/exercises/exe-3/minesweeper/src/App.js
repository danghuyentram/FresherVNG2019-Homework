import React, { Component } from 'react';
import './App.css';
import Header from './components/Header'
import Game from './components/Game'

class App extends Component {
    render() {
        return ( 
            <div className="container-fluid">
                <div className="row d-flex justify-content-center">
                    <Header></Header>
                </div>

                <div className="row d-flex justify-content-center">
                    <Game></Game>
                </div>

            </div>

        );
    }
}

export default App;