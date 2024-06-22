function salvarUsuario(){

    var id = $("#id").val();
    var nome = $("#nome").val();
    var idade = $("#idade").val();

    $.ajax({
     method: "POST",
     url: "salvar",
     data: JSON.stringify({id: id, nome: nome, idade: idade}),
     contentType: "application/json; charset-utf-8",
     success: function (response){
        $("#id").val(response.id)
        alert("Salvo com sucesso")
     }
    }).fail(function (xhr,status,errorThrown) {
        alert("Erro ao salvar: " + xhr.responseText)
    });


}


function carregarUsuariosDB() {

    $.ajax({
         method: "GET",
         url: "listatodos",
         contentType: "application/json; charset-utf-8",
         success: function (response){

            var tbody = $("#tbodyModalUsers");

            tbody.empty();

            for(var i = 0; i < response.length; i++) {

                var usuario = response[i];

                var tr = criarTr();


            }



         }
        }).fail(function (xhr,status,errorThrown) {
            alert("Erro ao salvar: " + xhr.responseText)
        });

}



function criarTr() {
    var tr = document.createElement("tr");
    return tr;
}


function visualizarUsuario(id) {

    $.ajax({
         method: "GET",
         url: "buscaruserid",
         data: "iduser=" + id,
         contentType: "application/json; charset-utf-8",
         success: function (response){
                $("#id").val() = response.id;
                $("#nome").val() = response.nome;
                $("#idade").val() = response.idade;

         }
        }).fail(function (xhr,status,errorThrown) {
            alert("Erro ao salvar: " + xhr.responseText)
        });



}


