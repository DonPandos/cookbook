import {Actions} from "./actionNames";

const initialState = {
    recipes: [],
    activeRecipe: null,
    isEdit: false,
    editRecipe: null,
    refresh: false
}

export const recipeReducer = (state = initialState, action) => {
    switch(action.type) {
        case Actions.SET_RECIPES:
            return { ...state, recipes: action.payload }
        case Actions.SET_ACTIVE_RECIPE:
            return { ...state, activeRecipe: action.payload }
        case Actions.SET_EDIT:
            return { ...state, isEdit: true, editRecipe: action.payload }
        case Actions.CANCEL_EDIT:
            return { ...state, isEdit: false }
        case Actions.REFRESH_RECIPES:
            if(state.refresh) return { ...state, refresh: false }
            else return { ...state, refresh: true }
        default:
            return state;
    }
}