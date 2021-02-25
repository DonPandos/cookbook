import s from './RecipeItem.module.css'
import {useDispatch} from "react-redux";
import {setActiveRecipe} from "../../reducers/recipe/actions";

const RecipeItem = (props) => {

    const dispatch = useDispatch();

    const handleOnNameClick = () => {
        dispatch(setActiveRecipe(props.recipe))
    }

    return(
        <div className={s.root}>
            <div>
                <p className={s.recipe_name} onClick={handleOnNameClick}>{props.recipe.name}</p>
            </div>
            <div>
                <p className={s.recipe_description}>{props.recipe.description}</p>
            </div>
        </div>
    )
}

export default RecipeItem;