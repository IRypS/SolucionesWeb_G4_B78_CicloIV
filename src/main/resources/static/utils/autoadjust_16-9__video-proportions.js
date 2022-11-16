// Funcion autoinvocada


( function() {

    let allVideoContainers = [...document.querySelectorAll('.c_img-video')];

    setNewHeigth();

    window.onresize = () => {
        setNewHeigth();
    }

    function setNewHeigth() {
        // Get reference from a first element
        let width = allVideoContainers[0].clientWidth;
        
        let calculateHeigth = (width * 0.5625).toString() + 'px';

        allVideoContainers.forEach( img => {
            img.style.height = calculateHeigth;
        });
    };

}) ();
