    var RegisteredClient = Backbone.Model.extend({
    	urlRoot: "/rest/registeredClients",
	    defaults: function() {
	      return {
	      };
	    }
    });

    var CreateClientView = Backbone.View.extend({
    	
    	el: $("#createClientDiv"),
    	
    	template: _.template($('#client-create').html()),
    	info_template: _.template($('#client-info').html()),
    	
    	events: {
    		"click #submit" : "submit",
    		"click #cancelButton" : "cancelEdit"
    	},
    	
    	initialize: function() {
    		this.model.bind('change', this.render, this);
    		
    		if (this.model.isNew())
    			this.render();
    	},
    	
    	render: function() {
    		this.$("#createClientForm").empty();
    		this.$("#createClientForm").append(this.template(this.model));
    		
    		return this;
    	},
    	
    	submit: function() {
    		var changedData = $("#createClientForm").serializeJSON();
    		var that = this;
    		this.model.save(changedData, { 
    			success: function(model, response, options) {
    				console.log(model);
    				that.$("#clientInfo").empty();
    				that.$("#clientInfo").append(that.info_template(model));
    			},
    			error: function() {
    				alert("An error occurred!");
    			}
    		});
    	},
    
    	cancelEdit: function() {
    		var result = confirm($.t("question.cancel"));
    		if (result)
    			controller.navigate("listInterviews", {trigger: true});
    	},
    });

    
    var client = new RegisteredClient();
    var editForm = new CreateClientView({model:client});