import React from 'react';
import { Router, Route, Link } from 'react-router-dom';
import BootstrapTable from 'react-bootstrap-table-next';
import { userService } from '../_services';
import 'bootstrap/dist/css/bootstrap.min.css';

class AdminPage extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            users: null,
            columns: [{
                dataField: 'id',
                text: 'Id'
            },
            {
                dataField: 'username',
                text: 'User Name',
                sort: true
            }, {
                dataField: 'firstName',
                text: 'First Name',
                sort: true
            },
            {
                dataField: 'lastName',
                text: 'Last Name',
                sort: true
            },
            {
                dataField: 'id',
                text: "Remove",
                editable: false,
                formatter: (cellContent, row) => {
                    const isUser = row.username === 'user';
                    {
                        if (isUser) {
                            return (

                                <button
                                    className="btn btn-danger btn-xs"
                                    onClick={() => this.handleDelete(row.id)}
                                >
                                    Delete
                                </button>
                            );
                        }
                    }

                },
            }]
        };


    }

    componentDidMount() {
        userService.getAll().then(users => this.setState({ users }));
    }

    handleDelete(id) {

        userService.deleteById(id).then( window.location.reload());
    }



    render() {
        const { users } = this.state;

        return (
            <div>
                <h1>Admin</h1>
                <p>This page can only be accessed by administrators.</p>
                <div>
                    All users from secure (admin only) api end point:


                    {users &&


                        <BootstrapTable
                            striped
                            hover
                            keyField='id'
                            data={users}
                            columns={this.state.columns} />
                    }
                </div>
            </div>
        );
    }
}

export { AdminPage };