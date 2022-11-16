// Funcion autoinvocada


( function() {

    let getCoverMovieImg = document.querySelector('.c_coverMovieImg');

    setNewHeigth();

    window.onresize = () => {
        setNewHeigth();
    }

    function setNewHeigth() {
        // Get reference from a first element
        let width = getCoverMovieImg.clientWidth;
        
        let calculateHeigth = (width * 1.4).toString() + 'px';

        getCoverMovieImg.style.height = calculateHeigth;
    };

}) ();
