import axios from 'axios'
import {setRecipes} from "../reducers/recipe/actions";

export default axios.create({
    baseURL: "http://178.209.88.232:8080/api/cookbook",
    responseType: "json"
});

