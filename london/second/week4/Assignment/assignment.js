var category_template, animals_template, modal_template;
var current_category = animals_data.category[0];
var current_animal = current_category.animals[0];


function showTemplate(template, data){
  var html    = template(data);
  $('#content').html(html);
}

$(document).ready(function(){


  var source = $("#category-template").html();
  category_template = Handlebars.compile(source);
  source = $("#animals-template").html();
  animals_template = Handlebars.compile(source);
  source = $("#modal-template").html();
  modal_template = Handlebars.compile(source);


  $("#buttonCat").click(function(){
    showTemplate(category_template,animals_data);

    $(".list-group-item").click(function(){
      var index = $(this).data("id");
      current_category = animals_data.category[index];
      showTemplate(animals_template,current_category);
      $(".animal_small").click(function(){
        var index = $(this).data("id");
        current_animal = current_category.animals[index];
        var html = modal_template(current_animal);
        $('#modal-container').html(html);
        $("#imageModal").modal('show');
      });
    });
  });

  $("#buttonPot").click(function(){
    showTemplate(animals_template,current_category);
    $(".animal_small").click(function(){
      var index = $(this).data("id");
      current_animal = current_category.animals[index];
      var html = modal_template(current_animal);
      $('#modal-container').html(html);
      $("#imageModal").modal('show');
    });
  });
});
