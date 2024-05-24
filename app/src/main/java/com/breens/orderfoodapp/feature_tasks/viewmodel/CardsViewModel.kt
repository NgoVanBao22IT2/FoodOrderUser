package com.breens.orderfoodapp.feature_tasks.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.breens.orderfoodapp.common.Result
import com.breens.orderfoodapp.data.model.Card
import com.breens.orderfoodapp.data.repositories.Repository
import com.breens.orderfoodapp.feature_tasks.events.CardsScreenUiEvent
import com.breens.orderfoodapp.feature_tasks.side_effects.CardScreenSideEffects
import com.breens.orderfoodapp.feature_tasks.state.CardsScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(private val cardRepository: Repository) : ViewModel() {

    private val _state: MutableStateFlow<CardsScreenUiState> = MutableStateFlow(CardsScreenUiState())
    val state: StateFlow<CardsScreenUiState> = _state.asStateFlow()

    private val _effect: Channel<CardScreenSideEffects> = Channel()
    val effect = _effect.receiveAsFlow()

    init {
        sendEvent(CardsScreenUiEvent.GetCards)
    }


    fun sendEvent(event: CardsScreenUiEvent) {
        reduce(oldState = _state.value, event = event)
    }

    private fun setEffect(builder: () -> CardScreenSideEffects) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }

    private fun setState(newState: CardsScreenUiState) {
        _state.value = newState
    }




    private fun reduce(oldState: CardsScreenUiState, event: CardsScreenUiEvent) {
        when (event) {


            CardsScreenUiEvent.GetCards -> {
                getCards(oldState = oldState)
            }
            is CardsScreenUiEvent.OnChangeUpdateCardDialogState -> {
                onUpdateAddCardDialog(oldState = oldState, isShown = event.show)
            }
            is CardsScreenUiEvent.OnChangeCardImage-> {
                onChangeCardImage(oldState = oldState, image = event.image)
            }
            is CardsScreenUiEvent.OnChangeCardBody -> {
                onChangeCardBody(oldState = oldState, body = event.body)
            }

            is CardsScreenUiEvent.OnChangeCardTitle -> {
                onChangeCardTitle(oldState = oldState, title = event.title)
            }
            is CardsScreenUiEvent.OnChangeCardPrice -> {
                onChangeCardPrice(oldState = oldState, price = event.price)
            }
            is CardsScreenUiEvent.OnChangeCardFavorite -> {
                onChangeCardFavorite(oldState = oldState, favorite = event.favorite)
            }
            is CardsScreenUiEvent.OnChangeCardViews -> {
                onChangeCardViews(oldState = oldState, views = event.views)
            }
            is CardsScreenUiEvent.OnChangeCardSales -> {
                onChangeCardSale(oldState = oldState, sale = event.sale)
            }

            is CardsScreenUiEvent.SetCardToBeUpdated -> {
                setCardToBeUpdated(oldState = oldState, card = event.cardToBeUpdated)
            }

            CardsScreenUiEvent.UpdateNote -> {
                updateNote(oldState = oldState)
            }

        }
    }

    private fun getCards(oldState: CardsScreenUiState) {
        viewModelScope.launch {
            setState(oldState.copy(isLoading = true))

            when (val result = cardRepository.getAllCards()) {
                is com.breens.orderfoodapp.common.Result.Failure -> {
                    setState(oldState.copy(isLoading = false))

                    val errorMessage =
                        result.exception.message ?: "An error occurred when getting your task"
                    setEffect { CardScreenSideEffects.ShowSnackBarMessage(messageCard = errorMessage) }
                }

                is com.breens.orderfoodapp.common.Result.Success -> {
                    val cards = result.data
                    setState(oldState.copy(isLoading = false, cards = cards))
                }
            }
        }
    }
    private fun updateNote(oldState: CardsScreenUiState) {
        viewModelScope.launch {
            setState(oldState.copy(isLoading = true))

            val image = oldState.imgUrl
            val title = oldState.currentTextFieldTitle
            val body = oldState.currentTextFieldBody
            val price = oldState.currentTextFieldPrice
            val favorite = oldState.currentTextFieldFavorite
            val views = oldState.currentTextFieldViews
            val sale = oldState.currentTextFieldSale
            val cardToBeUpdated = oldState.cardToBeUpdated

            when (
                val result = cardRepository.updateCard(
                    image = image,
                    title = title,
                    body = body,
                    price = price,
                    favorite = favorite,
                    views = views,
                    sale = sale,
                    cardId = cardToBeUpdated?.cardId ?: "",
                )
            ) {
                is Result.Failure -> {
                    setState(oldState.copy(isLoading = false))

                    val errorMessage =
                        result.exception.message ?: "An error occurred when updating task"
                    setEffect { CardScreenSideEffects.ShowSnackBarMessage(messageCard = errorMessage) }
                }

                is Result.Success -> {
                    setState(
                        oldState.copy(
                            isLoading = false,
                            imgUrl = "",
                        ),
                    )


                    setEffect { CardScreenSideEffects.ShowSnackBarMessage(messageCard = "Status updated successfully") }

                    sendEvent(CardsScreenUiEvent.GetCards)
                }
            }
        }
    }




    private fun onUpdateAddCardDialog(oldState: CardsScreenUiState, isShown: Boolean) {
        setState(oldState.copy(isShowUpdateCardDialog = isShown))
    }

    private fun onChangeCardImage(oldState: CardsScreenUiState, image: String) {
        setState(oldState.copy(imgUrl = image))
    }

    private fun onChangeCardBody(oldState: CardsScreenUiState, body: String) {
        setState(oldState.copy(currentTextFieldBody = body))
    }

    private fun onChangeCardTitle(oldState: CardsScreenUiState, title: String) {
        setState(oldState.copy(currentTextFieldTitle = title))
    }

    private fun onChangeCardPrice(oldState: CardsScreenUiState, price: Int) {
        setState(oldState.copy(currentTextFieldPrice = price))
    }
    private fun onChangeCardFavorite(oldState: CardsScreenUiState, favorite: Int) {
        setState(oldState.copy(currentTextFieldFavorite = favorite))
    }
    private fun onChangeCardViews(oldState: CardsScreenUiState, views: Int) {
        setState(oldState.copy(currentTextFieldViews = views))
    }
    private fun onChangeCardSale(oldState: CardsScreenUiState, sale: Int) {
        setState(oldState.copy(currentTextFieldSale = sale))
    }
    private fun setCardToBeUpdated(oldState: CardsScreenUiState, card: Card) {
        setState(oldState.copy(cardToBeUpdated = card))
    }

}
