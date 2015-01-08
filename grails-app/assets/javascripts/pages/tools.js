(function ($, undefined) {



    function initBox() {

        $(".span-left").each(function () {
            var $this = $(this);
            var type = $(this).attr("value");
            if (type === "SDM") {
                $this.addClass("sdm");
                return
            }
            if (type === "Basic") {
                $this.addClass("basic");
                return
            }
            else {
                $this.addClass("outcome");
            }
        })
    }

    function triggerButtonDropdown(){
        $(".btn-dropdown").on("click", function(){
            var $this = $(this);
            var $parent = $this.parent();
            var isActive = $parent.hasClass('open');

            if(!isActive){
                $parent.addClass('open');
                return
            }
            $parent.removeClass('open');

        })



    }

    initBox();
    triggerButtonDropdown();





})(jQuery);
