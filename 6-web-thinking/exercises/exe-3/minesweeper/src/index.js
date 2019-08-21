import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
// import App from './App.js';
import Game from './components/Game.jsx';
import { Provider } from 'react-redux';
import {createStore} from 'redux';


const defaultState ={
    height:10,
    width:10,
    mines:10,
}

const reducer = (state = defaultState, action) =>{

    if (action.type === "NEWBOARD")
        return {
            height:action.height,
            width:action.width,
            mines:action.mines,
        }

    return  state;
}

const store = createStore(reducer);



ReactDOM.render( 
    <Provider store={store}>
        <Game>{console.log(store.getState())}</Game> 
    </Provider>
    ,
    document.getElementById('root')
);