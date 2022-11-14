function addInputToDom(idContainer, idSelectorElement, nameListParam) {

    let selectorElement = document.querySelector(idSelectorElement);
    let idSelect = selectorElement.value;
    let name = selectorElement.options[selectorElement.selectedIndex].text;

    let template = `<div class="mt-3">
                        <div class="d-flex justify-content-between">
                            <label for="` + idSelect + `" class="form-label"><small>` + name + `</small></label>
                            <div onclick="deleteInputElement(this)" ><img src="/resources/icons/delete__icon.svg" alt=""></div>
                        </div>
                        <input id="` + idSelect + `" name="` + nameListParam + `[]" value="` + idSelect + `"
                            type="text" class="list-group-item list-group-item-action">
                    </div>`;
                    
    if (idSelect == '0' || idSelect == 0 || idSelect == null ) {} else {
        document.querySelector(idContainer).insertAdjacentHTML("afterbegin", template);
    }

};

function deleteInputElement(element) {
    element.parentElement.parentElement.remove();
};