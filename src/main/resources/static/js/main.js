$(document).ready(
    function () {

        /* Edit profiles */
        $("#first_name-check").hide();
        $("#last_name-check").hide();
        $("#phone-check").hide();
        $("#desc-check").hide();

        $(".edit-form").submit(
            function (event) {
                $("#first_name-check").hide();
                $("#last_name-check").hide();
                $("#phone-check").hide();
                $("#desc-check").hide();

                let inputs = $(".edit-form :input[type != submit]");
                let valid = true
                let value;

                inputs.each(
                    function () {
                        value = $(this).val().trim();
                        if (this.id == "firstname") {
                            if (value.length == 0) {
                                $("#first_name-check").text("First name is required");
                                $("#first_name-check").show();
                                valid = false;
                            }
                            else if (value.length > 50) {
                                $("#first_name-check").text("First name length must be between 3 and 30");
                                $("#first_name-check").show();
                                valid = false;
                            }
                            else {
                                $("#first_name-check").hide();
                            }
                        }
                        if (this.id == "lastname") {
                            if (value.length == 0) {
                                $("#last_name-check").text("Last name is required");
                                $("#last_name-check").show();
                                valid = false;
                            }
                            else if (value.length > 50) {
                                $("#last_name-check").text("Last name must have less than 50 characters");
                                $("#last_name-check").show();
                                valid = false;
                            }
                            else {
                                $("#last_name-check").hide();
                            }
                        }
                    }
                )

                if(valid) {
                    $(".edit-form").submit();
                }
                event.preventDefault();
            }
        )

        /* Add content */
        $("#titleCheck").hide();
        $("#briefCheck").hide();
        $("#contentCheck").hide();
        $(".content-list-form").submit(
            function (event) {
                $("#titleCheck").hide();
                let valid = true;
                let value = $("#title").val().trim();
                if (value.length == 0) {
                    $("#titleCheck").text("Title is required");
                    $("#titleCheck").show();
                    valid = false;
                }
                else if (value.length > 50) {
                    $("#titleCheck").text("Title must have less than 50 characters");
                    $("#titleCheck").show();
                    valid = false;
                }
                else {
                    $("#titleCheck").hide();
                }
                if (valid) {
                    $(".content-list-form").submit();
                }
                event.preventDefault();
            }
        )


        /* Edit content */
        $(".content-update-form").submit(
            function (event) {
                $("#titleCheck").hide();
                let valid = true;
                let value = $("#title").val().trim();
                if (value.length == 0) {
                    $("#titleCheck").text("Title is required");
                    $("#titleCheck").show();
                    valid = false;
                }
                else if (value.length > 50) {
                    $("#titleCheck").text("Title must have less than 50 characters");
                    $("#titleCheck").show();
                    valid = false;
                }
                else {
                    $("#titleCheck").hide();
                }
                if (valid) {
                    $(".content-update-form").submit();
                }
                event.preventDefault();
            }
        )


        /* Loading view content */
        $("#view-content").click(
            function () {
                $(".content").html('<h2 class="p-3 h2 mt-3"> Loading </h2>');
                setTimeout(() => {
                    $("body .content").load("view-content.html .content")
                }, 5000);
            }
        )
    }
)
/*

I was having a similar problem and I found ignoring the cache or clearing browser cache to do the trick.
Ignoring the cache: hit ctrl + F5 to refresh page ignoring the cache.
Clearing the cache: hit shift + ctrl + delete will bring you to a screen where you can select the option to clear cached images and files.
 */