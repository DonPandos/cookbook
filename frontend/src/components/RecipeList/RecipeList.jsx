import s from './RecipeList.module.css'
import {useSelector} from "react-redux";
import RecipeItem from "./RecipeItem";

const RecipeList = (props) => {

    const recipes = useSelector(state => state.recipe.recipes);

    const recipeItems = [];

    for(const [index, value] of recipes.entries()) {
        recipeItems.push(<RecipeItem recipe={value}/>)
    }

    return(
        <div className={s.root}>
            {recipeItems}
        </div>
    )
}

export default RecipeList;