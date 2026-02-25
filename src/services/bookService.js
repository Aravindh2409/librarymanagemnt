import axios from "axios";

const API_URL = "http://localhost:8080/api/books";

export const getBooks = () => axios.get(API_URL);
export const addBook = (book) => axios.post(API_URL, book);
export const deleteBook = (id) => axios.delete(`${API_URL}/${id}`);