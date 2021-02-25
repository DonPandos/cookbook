import s from './RecipeHistoryItem.module.css'
import {timestampToDateTimeString} from "../../utils/DateUtil";
import {useDispatch} from "react-redux";
import {setActiveRecipe} from "../../reducers/recipe/actions";

const RecipeHistoryItem = (props) => {

    const dispatch = useDispatch();

    const handleOnClick = () => {
        dispatch(setActiveRecipe(props.recipe))
    }

    return (
        <div className={s.root} onClick={handleOnClick}>
            <span className={s.history_item}>{props.recipe.name + ' ' + timestampToDateTimeString(props.recipe.modifyAt)}</span>
        </div>
    )
}

export default RecipeHistoryItem;