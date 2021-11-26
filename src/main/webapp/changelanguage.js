function submitLanguage(){
    var option = $.querySelector('#language');
    var value = option.value;
    console.log(option)
    console.log(value)
    value.setAttribute("selected");
    option.form.submit()
}

console.log("hello")