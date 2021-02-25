import CookbookAPI from "./CookbookAPI";
import {setRecipes} from "../reducers/recipe/actions";
import {store} from "../store/configureStore";

export const refreshRecipesData = () => {
    CookbookAPI.get('/recipes')
        .then((response) => {
            store.dispatch(setRecipes(response.data))
        })
}