const {Actions} = require("./actionNames");

const setRecipes = (recipeList) => {
    return {
        type: Actions.SET_RECIPES,
        payload: recipeList
    }
}

const setActiveRecipe = (recipe) => {
    return {
        type: Actions.SET_ACTIVE_RECIPE,
        payload: recipe
    }
}

const setEdit = (recipe) => {
    return {
        type: Actions.SET_EDIT,
        payload: recipe
    }
}

const cancelEdit = () => {
    return {
        type: Actions.CANCEL_EDIT
    }
}
const refreshRecipes = () => {
    return {
        type: Actions.REFRESH_RECIPES,
        payload: true
    }
}

export {
    setRecipes,
    setActiveRecipe,
    setEdit,
    refreshRecipes,
    cancelEdit
}