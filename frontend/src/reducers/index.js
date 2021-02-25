import {recipeReducer} from "./recipe";
import {combineReducers} from "redux";

export const rootReducer = combineReducers({
    recipe: recipeReducer
})