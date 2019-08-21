import React, { Component } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import FormHeader from './FormHeader'
import {connect} from 'react-redux';



class Header extends Component {
    
    render() {
        return(
            
            <div className="container header">
                <div className="row d-flex justify-content-center">
                    
                    <h1>
                        Minesweeper
                    </h1>
                </div>
                <div className="row d-flex justify-content-center">
                    <FormHeader></FormHeader>
                </div>
                
                
            </div>
        );
    }

    
}



export default connect()(Header);
