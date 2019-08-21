import React, { Component } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';


class Test extends Component {
  render() {
    return (
        <ul class="nav nav-pills nav-fill">
        <li class="nav-item">
            <a class="nav-link " href="#">Active</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">Much longer nav link</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="#">Link</a>
        </li>
        <li class="nav-item">
            <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
        </li>
        </ul>
    );
  }
}

export default Test;