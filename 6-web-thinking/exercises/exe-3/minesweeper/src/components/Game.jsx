import React, { Component } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import Board from './Board.jsx';
import Header from './Header.jsx';
import {connect} from 'react-redux';


class Game extends Component {


    render() {

      return (
            <div className="container-fluid" id="game">
                
                <div className="row d-flex justify-content-center" id="Header">
                    <Header></Header>
                </div>

                <div className="row d-flex justify-content-center game">
                    <Board />
                </div>

            </div>
      );
    }
  }


export default connect()(Game);

