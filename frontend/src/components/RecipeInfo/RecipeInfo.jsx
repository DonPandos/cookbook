import {useDispatch, useSelector} from "react-redux";
import s from './RecipeInfo.module.css'
import {timestampToDateTimeString} from "../../utils/DateUtil";
import {refreshRecipes, setActiveRecipe, setEdit} from "../../reducers/recipe/actions";
import CookbookAPI from "../../utils/CookbookAPI";
import {refreshRecipesData} from "../../utils/CookbookAPIUtils";
import {useEffect, useState} from "react";
import RecipeItem from "../RecipeList/RecipeItem";
import RecipeHistoryItem from "./RecipeHistoryItem";

const RecipeInfo = (props) => {

    const [ history, setHistory ] = useState(null);

    const recipe = useSelector(state => state.recipe.activeRecipe);
    const dispatch = useDispatch();

    const handleOnDeleteButtonClick = () => {
        CookbookAPI.delete('/recipes/' + recipe.id)
            .then(response => {
                dispatch(setActiveRecipe(null))
                dispatch(refreshRecipes())
            })
            .catch(error => {
                alert('Couldn\'t delete parent recipe')
            })
    }

    const handleOnParentRecipeButtonClick = () => {
        dispatch(setActiveRecipe(recipe.parentRecipe))
    }

    const handleOnEditButtonClick = () => {
        dispatch(setEdit(recipe))
    }

    const recipeHistory = () => {
        CookbookAPI.get('/recipes/' + recipe.id + '/history')
            .then(response => {
                setHistory(response.data)
            })
    }


    if(recipe != null) {
        let dateTime
        let historyItems = [];
        if(recipe.createdAt != null) {
            dateTime = timestampToDateTimeString(recipe.createdAt);

            recipeHistory();
            historyItems.push(<span>Recipe history: </span>)

            if(history != null)
                for(const [index, value] of history.entries()) {
                    historyItems.push(<RecipeHistoryItem recipe={value}/>)
                }
        }
        else dateTime = timestampToDateTimeString(recipe.modifyAt);



        return (
            <div className={s.root}>
                <div className={s.recipe_name}>
                    {recipe.name}
                </div>
                <div className={s.recipe_date_of_creation}>
                    {recipe.createdAt != null ? 'Date of creation' : 'Date of modified'} : {dateTime}</div>
                <div>{recipe.description}</div>
                {recipe.parentRecipe != null &&
                <div className={s.recipe_parent_recipe}>
                    Parent recipe: <span onClick={handleOnParentRecipeButtonClick}>{recipe.parentRecipe.name}</span>
                </div>
                }
                <div className={s.operation_button}>
                    <span onClick={handleOnEditButtonClick}>Edit</span>
                </div>
                <div className={s.operation_button}>
                    <span onClick={handleOnDeleteButtonClick}>Delete</span>
                </div>
                <div>
                    { historyItems }
                </div>

            </div>
        )
    }
    else return (
        <div>

        </div>
    )
}

export default RecipeInfo;