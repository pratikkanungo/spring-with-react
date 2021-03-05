import config from 'config';
import { authHeader, handleResponse } from '../_helpers';
export const userService = {
    getAll,
    getById,
    deleteById
};

function getAll() {
    const requestOptions = { method: 'GET'};
    return fetch(`${config.apiUrl}/v1/getAllCustomers`, requestOptions).then(handleResponse);
}

function getById(id) {
    const requestOptions = { method: 'GET'};
    return fetch(`${config.apiUrl}/v1/getCustomerById/${id}`, requestOptions).then(handleResponse);
}

function deleteById(id) {
    const requestOptions = { method: 'DELETE' };
    return fetch(`${config.apiUrl}/v1/removeCustomer/${id}`, requestOptions).then(handleResponse);
}