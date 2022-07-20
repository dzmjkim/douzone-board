import axios from "axios";

const config = {
  withCredentials: true,
};

export const getFetcher = (url) => axios.get(url).then((res) => res.data);
export const postFetcher = (url, data) => axios.post(url, data, config ).then((res) => res.data).catch((res) => res.error);
export const getTokenFetcher = (url, token) =>
  axios.get(url, { headers: { Authorization: "Bearer " + token } }).then((res) => res.data);
export const postTokenFetcher = (url, data, token) =>
  axios.post(url, data, { headers: { Authorization: "Bearer " + token } }).then((res) => res.data);
