// Funcion autoinvocada


( function() {

    let getContainerWidth = document.querySelector('.c_img-video').clientWidth;

    let allVideoContainers = [...document.querySelectorAll('.c_img-video')];

    setNewHeigth();

    // window.onresize = () => {
    //     setNewHeigth();
    // }

    window.addEventListener('resize', () => {
        setNewHeigth();
    });

    function setNewHeigth() {
        let calculateHeigth = (getContainerWidth * 0.5625).toString() + 'px';

        allVideoContainers.forEach( img => {
            img.style.height = calculateHeigth;
        });
    };
}) ();
