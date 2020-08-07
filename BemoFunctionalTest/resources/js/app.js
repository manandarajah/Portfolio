/**
 * First we will load all of this project's JavaScript dependencies which
 * includes Vue and other libraries. It is a great starting point when
 * building robust, powerful web applications using Vue and Laravel.
 */

require('./bootstrap');

window.Vue = require('vue');

/**
 * The following block of code may be used to automatically register your
 * Vue components. It will recursively scan this directory for the Vue
 * components and automatically register them with their "basename".
 *
 * Eg. ./components/ExampleComponent.vue -> <example-component></example-component>
 */

// const files = require.context('./', true, /\.vue$/i);
// files.keys().map(key => Vue.component(key.split('/').pop().split('.')[0], files(key).default));

Vue.component('example-component', require('./components/ExampleComponent.vue').default);

Vue.component('flexi-tag', {
	data() {
		return {
			counter: 0
		}
	},
	methods: {
		changeTag: function() {
			++this.counter;
		}
	},
	render: function(createElement) {
		var tag = "span";
		
		switch (this.counter) {
			case 1:
				tag = "h1";
				break;
			case 2:
				tag = "h2";
				break;
			case 3:
				tag = "h3";
				break;
			default:
				tag = "span";
				this.counter = 0;
		}
		
		return createElement(tag, { on: {click: this.changeTag }}, this.$slots.default);
	}
});

/**
 * Next, we will create a fresh Vue application instance and attach it to
 * the page. Then, you may begin adding components to this application
 * or customize the JavaScript scaffolding to fit your unique needs.
 */

const app = new Vue({
    el: '#app',
	data: {
		metatitle: 'BeMo Academic Consulting',
		metadescription: 'FREE Ultimate Guide to CDA Interviews: Tips & Proven Strategies to Help You Prepare & Ace Your CDA Structured Interview.',
		isMainImage: true,
		descriptionTitle: 'Ultimate Guide to CDA Structured Interview: Tips & Proven Strategies to Help You Prepare & Ace Your CDA Interview',
		overview: 'Overview:',
		purpose: 'What is the purpose of the dental school interview?',
		contents: 'History, rationale, and the structure of the CDA interview',
		questions: 'Types of Questions',
		competencies: 'The Seven Competencies',
		consulting: 'BeMo Academic Consulting Inc.',
		number: '1-855-900-BeMo (2366)',
		email: 'info@bemoacademicconsulting.com',
		linkedEmail: '',
		dtStyleObject: {
			font: '17px Arial, Verdana, Helvetica, sans-serif',
			fontWeight: 'bold',
			color: '#000066'
		},
		StyleObject2: {
			fontSize: '17px',
			fontWeight: 'bold'
		}
	},
	methods: {
		switchImage: function() {
			this.isMainImage = !this.isMainImage;
		},
		noIndex: function() {
			document.querySelector("#wrapper").innerText = "No-Index";
		},
		changeText: function() {
			this.descriptionTitle = 'This text has changed';
			this.overview = "Review:";
			this.purpose = "My purpose";
			this.contents = "My history, my rationale, and my structure to life";
			this.questions = "FAQs";
			this.competencies = "7 Competencies";
			
			this.consulting = "Mugiesshan's consulting services";
			this.number = "(999)-999-9999";
			this.email = "mugiesshan.anandarajah@hotmail.com";
			console.log("clicked on change text");
		},
		changeFont: function() {
			this.dtStyleObject.fontWeight = 'Italic';
			this.dtStyleObject.color = "red";
			this.dtStyleObject.font = "17px Times New Roman, Calibri, Candara";
		},
		changeFont2: function() {
			this.StyleObject2.fontSize = "100px";
			this.StyleObject2.fontWeight = 'Italic'; 
			console.log("clicked on change text");
		}
	}
});
