import axios from 'axios';

const serviceCommonUrl = '';
const loginUrl = '';
const gwUrl = 'https://localhost:8080';

export const keySign = 'sign';
const url = {
  dataJpa: 'http://localhost:8042/data-jpa',
  serviceCommon: 'http://localhost:8030/service-common'
};

export function getCommonInstance(contextName: string) {
  return axios.create({ baseURL: `${url[contextName]}`, headers: { 'Content-Type': 'application/json' } });
}
