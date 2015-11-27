<style>
	#map-canvas {
		margin: 0;
		padding: 0;
		width: 100%;
		height: 600px;
	}
</style>

<script src="https://maps.googleapis.com/maps/api/js?sensor=true"></script>
<script>
	var radius = 100;
	
	function setRadius(userRadius) {
		radius = userRadius;
		geoloc();
	}

	function geoloc() {
		if (navigator.geolocation) {
			var optn = {
				enableHighAccuracy: true,
				timeout: Infinity,
				maximumAge: 0
			};
			watchId = navigator.geolocation.getCurrentPosition(showPosition, showError, optn);
		} else {
			alert('Geolocation is not supported in your browser');
		}
	}

	function showPosition(position) {
		setGeoValues(position.coords.latitude, position.coords.longitude);
		var googlePos = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
		var mapOptions = {
			zoom: 18,
			center: googlePos,
			mapTypeId: google.maps.MapTypeId.ROADMAP
		};
		var mapObj = document.getElementById('map-canvas');
		var googleMap = new google.maps.Map(mapObj, mapOptions);
		var markerOpt = {
			map: googleMap,
			position: googlePos,
			draggable: true,
			title: 'Hi , I am here',
			animation: google.maps.Animation.DROP
		};
		var googleMarker = new google.maps.Marker(markerOpt);
		var geocoder = new google.maps.Geocoder();
		geocoder.geocode({
			'latLng': googlePos
		}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				if (results[1]) {
					var popOpts = {
						content: results[1].formatted_address,
						position: googlePos
					};
					var popup = new google.maps.InfoWindow(popOpts);
					google.maps.event.addListener(googleMarker, 'click', function() {
						popup.open(googleMap);
					});
					google.maps.event.addListener(googleMarker, 'dragend', function() {
						var lat = googleMarker.getPosition().lat();
						var lng = googleMarker.getPosition().lng();
						setGeoValues(lat, lng);
					});
				} else {
					alert('No results found');
				}
			} else {
				alert('Geocoder failed due to: ' + status);
			}
		});

		// Add circle overlay and bind to marker
		var circle = new google.maps.Circle({
			map: googleMap,
			radius: radius,
			fillColor: '#EC971F',
			strokeWeight: 2
		});
		circle.bindTo('center', googleMarker, 'position');

		$('#buttonNext').prop('disabled', false);
	}

	function setGeoValues(lat,lng) {
		document.userLocation.userLatitude.value = lat;
		document.userLocation.userLongitude.value = lng;
		document.userLocation.userRadius.value = radius;
	}

	function showError(error) {
		var err = document.getElementById('map-canvas');
		switch (error.code) {
			case error.PERMISSION_DENIED:
				err.innerHTML = "User denied the request for Geolocation."
				break;
			case error.POSITION_UNAVAILABLE:
				err.innerHTML = "Location information is unavailable."
				break;
			case error.TIMEOUT:
				err.innerHTML = "The request to get user location timed out."
				break;
			case error.UNKNOWN_ERROR:
				err.innerHTML = "An unknown error occurred."
				break;
		}
	}
</script>