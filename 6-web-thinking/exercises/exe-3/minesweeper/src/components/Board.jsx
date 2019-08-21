import React, { Component } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import Cell from './Cell'
import PropTypes from 'prop-types';
import {connect} from 'react-redux';




class Board extends Component {
    constructor(props){
      super(props);

      this.state={
        height:this.props.height,
        width: this.props.width,
        mines: this.props.mines,
    }
  }


  
    // get mines
    getMines(data) {
      let mineArray = [];
  
      data.map(datarow => {
        datarow.map((dataitem) => {
          if (dataitem.isMine) {
            mineArray.push(dataitem);
          }
        });
      });
  
      return mineArray;
    }
  

    // get random number given a dimension
    getRandomNumber(dimension) {
      // return Math.floor(Math.random() * dimension);
      return Math.floor((Math.random() * 1000) + 1) % dimension;
    }
  
    // Gets initial board data
    initBoardData(height, width, mines) {
      let data = this.createEmptyArray(height, width);
      data = this.createMines(data, height, width, mines);
      data = this.getNeighbours(data, height, width);
      return data;
    }
  
    createEmptyArray(height, width) {
      let data = [];
  
      for (let i = 0; i < height; i++) {
        data.push([]);
        for (let j = 0; j < width; j++) {
          data[i][j] = {
            x: i,
            y: j,
            isMine: false,
            neighbour: 0,
            isEmpty: false,
          };
        }
      }
      return data;
    }
  
    // plant mines on the board
    createMines(data, height, width, mines) {
      let randomx, randomy, minesPlanted = 0;
  
      while (minesPlanted < mines) {
        randomx = this.getRandomNumber(height);
        randomy = this.getRandomNumber(width);
        if (!(data[randomx][randomy].isMine)) {
          data[randomx][randomy].isMine = true;
          minesPlanted++;
        }
      }
  
      return (data);
    }
  
    // get number of neighbouring mines for each board cell
    getNeighbours(data, height, width) {
      let updatedData = data;
  
      for (let i = 0; i < height; i++) {
        for (let j = 0; j < width; j++) {
          if (data[i][j].isMine !== true) {
            let mine = 0;
            const area = this.getNeighboursAround(data[i][j].x, data[i][j].y, data);
            area.map(value => {
              if (value.isMine) {
                mine++;
              }
            });
            if (mine === 0) {
              updatedData[i][j].isEmpty = true;
            }
            updatedData[i][j].neighbour = mine;
          }
        }
      }
  
      return (updatedData);
    };
  
    // looks for neighbouring cells and returns them
    getNeighboursAround(x, y, data) {
      const el = [];
  
      //up
      if (x > 0) {
        el.push(data[x - 1][y]);
      }
  
      //down
      if (x < this.props.height - 1) {
        el.push(data[x + 1][y]);
      }
  
      //left
      if (y > 0) {
        el.push(data[x][y - 1]);
      }
  
      //right
      if (y < this.props.width - 1) {
        el.push(data[x][y + 1]);
      }
  
      // top left
      if (x > 0 && y > 0) {
        el.push(data[x - 1][y - 1]);
      }
  
      // top right
      if (x > 0 && y < this.props.width - 1) {
        el.push(data[x - 1][y + 1]);
      }
  
      // bottom right
      if (x < this.props.height - 1 && y < this.props.width - 1) {
        el.push(data[x + 1][y + 1]);
      }
  
      // bottom left
      if (x < this.props.height - 1 && y > 0) {
        el.push(data[x + 1][y - 1]);
      }
  
      return el;
    }
  
 

    getCells(datarow){
        return datarow.map((dataitem) =>{
          console.log("k")

            return (
                <td >
                <Cell
                    value={dataitem}
                />
                {(datarow[datarow.length - 1] === dataitem) ? <div className="clear" /> : ""}
                </td>);
            })
    }
         
     
  
    renderBoard(data) {
        return data.map((datarow) => {
            return (
                <tr>
                    {this.getCells(datarow)}
                </tr>
            );
        })
  
    }
    

   
    render() {
      return (
            <table >
                  <tbody className="board">
                  {
                    this.renderBoard(this.initBoardData(this.props.height,this.props.width,this.props.mines))
                  }
                </tbody>
            </table>
      );
    }

  }

  Board.propTypes = {
    height: PropTypes.number,
    width: PropTypes.number,
    mines: PropTypes.number,
  }

function mapStatetoProps(state){

  return{
    height: state.height,
    width: state.width,
    mines: state.mines,
  }
}



export default connect(mapStatetoProps)(Board);
