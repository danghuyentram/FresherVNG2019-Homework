import React from 'react';
import sakura from '../media/sakura.png';

export default class Cell extends React.Component {
  getValue() {
    const { value } = this.props;

    if (value.isMine) {
      return (          
          <img src={sakura} width="30px" height="30px" ></img>
      );
    }
    if (value.neighbour === 0) {
      return null;
    }
    return value.neighbour;
  }

  render() {
    const { value} = this.props;
    let className =
      "cell" +
      (value.isMine ? "-is-mine" : "");

    return (
      <div className={className}>
        {this.getValue()}
      </div>
    );
  }
}

