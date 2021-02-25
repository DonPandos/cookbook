import s from './AddRecipe.module.css'
import {useForm} from "react-hook-form";
import {Autocomplete} from "@material-ui/lab";
import {Button, TextareaAutosize, TextField} from "@material-ui/core";
import {useDispatch, useSelector} from "react-redux";
import {useEffect, useState} from "react";
import CookbookAPI from "../../utils/CookbookAPI";
import {refreshRecipesData} from "../../utils/CookbookAPIUtils";
import {cancelEdit, refreshRecipes} from "../../reducers/recipe/actions";

const AddRecipe = (props) => {

    const { register, errors, handleSubmit } = useForm()
    const [ parentRecipe, setParentRecipe ] = useState(null);
    const dispatch = useDispatch();
    const recipes = useSelector(state => state.recipe.recipes)
    const isEdit = useSelector(state => state.recipe.isEdit)
    const editRecipe = useSelector(state => state.recipe.editRecipe)

    const handleOnFormSubmit = data => {
        if(isEdit) {
            CookbookAPI.put('/recipes', {
                id: editRecipe.id,
                name: data.name,
                description: data.description,
                parentId: parentRecipe == null ? null : parentRecipe.id
            }, {
                headers: {
                    'Access-Control-Allow-Origin': '*',
                    'Content-Type': 'application/json'
                }
            }).then(response => {
                dispatch(cancelEdit())
            }).catch(error => {
                alert('Recipe with this name already exists');
            })
        } else {
            CookbookAPI.post('/recipes', {
                name: data.name,
                description: data.description,
                parentId: parentRecipe == null ? null : parentRecipe.id
            }, {
                headers: {
                    'Access-Control-Allow-Origin': '*',
                    'Content-Type': 'application/json'
                }
            }).catch(error => {
                alert('Recipe with this name already exists');
            })
        }
        dispatch(refreshRecipes({}))
    }

    const handleOnCancelClick = () => {
        dispatch(cancelEdit())
    }

    const handleOnParentRecipeChange = (event, value) => {
        setParentRecipe(value);
    }


    return (
        <div className={s.root}>
            <form onSubmit={handleSubmit(handleOnFormSubmit)}>
                <p>
                    {isEdit ?
                        <input className={s.input_field} name={'name'}  ref={register({ required: true, maxLength: 50 })} placeholder={'Recipe name'} defaultValue={editRecipe.name}/> :
                        <input className={s.input_field} name={'name'}  ref={register({ required: true, maxLength: 50 })} placeholder={'Recipe name'} />
                    }
                    {errors.name && "Recipe name is required"}
                </p>
                <p>
                    {isEdit ?
                        <textarea className={s.input_field} name={'description'} ref={register({required:  true})} placeholder={'Recipe description'} defaultValue={editRecipe.description}/> :
                        <textarea className={s.input_field} name={'description'} ref={register({required:  true})} placeholder={'Recipe description'}/>
                    }
                    {errors.description && "Recipe description is required"}
                </p>
                <p>
                    {isEdit ?
                        <Autocomplete
                            name={'parentRecipe'}
                            renderInput={(params) => <TextField {...params} label="Parent recipe" variant="outlined" />}
                            options={recipes}
                            getOptionLabel={(option) => option.name}
                            getOptionSelected={(option, value) => option.id === value.id}
                            onChange={(event, value) =>handleOnParentRecipeChange(event, value)}
                            >
                        </Autocomplete>
                        :
                        <Autocomplete
                            name={'parentRecipe'}
                            renderInput={(params) => <TextField {...params} label="Parent recipe" variant="outlined" />}
                            options={recipes}
                            getOptionLabel={(option) => option.name}
                            getOptionSelected={(option, value) => option.id === value.id}
                            onChange={(event, value) =>handleOnParentRecipeChange(event, value)}
                            >
                        </Autocomplete>
                    }

                </p>
                <p>
                    {isEdit ?
                        <div>
                            <Button type={'submit'} variant={"contained"} color={"primary"}>Save recipe</Button>
                            <Button variant={"contained"} color={"secondary"} onClick={handleOnCancelClick}>Cancel</Button>
                        </div>
                        :
                        <Button type={'submit'} variant={"contained"} color={"primary"}>Add recipe</Button>
                    }
                </p>
            </form>
        </div>
    )
}

export default AddRecipe;