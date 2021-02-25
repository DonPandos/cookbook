import './App.css';
import RecipeList from "./components/RecipeList/RecipeList";
import {useEffect} from "react";
import AddRecipe from "./components/AddRecipe/AddRecipe";
import {refreshRecipesData} from "./utils/CookbookAPIUtils";
import RecipeInfo from "./components/RecipeInfo/RecipeInfo";
import {useSelector} from "react-redux";

const App = (props) => {

    const refresh = useSelector(state => state.recipe.refresh)

    useEffect(() => {
        refreshRecipesData({})
    }, [refresh])


    useEffect(() => {
        refreshRecipesData({})
    }, [])

    return (
        <div className="App">
            <div className="first-column">
                <AddRecipe />
                <RecipeList />
            </div>
            <div className="second-column">
              <RecipeInfo />
            </div>
        </div>
    );
}

export default App;
