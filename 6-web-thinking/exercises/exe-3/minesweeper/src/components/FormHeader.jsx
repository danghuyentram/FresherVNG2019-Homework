import React, { Component } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {connect} from 'react-redux';


class FormHeader extends Component{
    constructor() {
        super();
        
        this.state = {
            height: '10',
           width: '10',
           mines: '10'
        }
        this.updateState = this.updateState.bind(this);
     };

     
     updateState(e) {
         const target = e.target;

        if(target.id ==='height')
            this.setState({height: e.target.value});
        else if(target.id ==='width')
            this.setState({width: e.target.value});
            else
                this.setState({mines: e.target.value});

     }


    onClick = ()=>{
        if(this.state.height*this.state.width>=this.state.mines)
            this.props.onClick();
        else
            alert("Mines is over than number of cells");

    }

  
    render() {
        return ( 
                <div  className="row d-flex justify-content-center">
                    <form className="form-inline" >
                            <label htmlFor="height" >
                                Height:
                            </label>
                            <input id="height" type="number" value={this.state.height} onChange={this.updateState} />

                            <label htmlFor="width" >
                                Width:
                            </label>
                            <input id="width" type="number" value={this.state.width} onChange={this.updateState} />

                            <label htmlFor="mines" >
                                Mines:
                            </label>
                            <input id="mines" type="number" value={this.state.mines} onChange={this.updateState} />

                            <button type="button" className="btn btn-primary mb-2" 
                            onClick={this.onClick}>
                                Submit</button>                      
                    </form>
                </div>

        );
}

}



const mapDispatchToProps = dispatch =>({
    onClick: () =>
        dispatch({
            type:"NEWBOARD",
            height: parseInt(document.getElementById("height").value),
            width: parseInt(document.getElementById("width").value),
            mines:parseInt(document.getElementById("mines").value),
        })
   
    
})



export default connect(null,mapDispatchToProps)(FormHeader);
