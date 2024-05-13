$(document).ready(function () {
    function handleCityPictureClick(cityName, cityCard) {
        let newDate = new Date();
        let dateStr = newDate.toLocaleDateString();
        // Send request to get weather information
        $.get(`http://localhost:8080/api/cities/${cityName}/weather`, function (weatherInfo) {
            let weatherImage;
            if (weatherInfo.current.temp_c < 10) {
                weatherImage = "https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-weather/ilu1.webp";
            } else {
                weatherImage = "https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-weather/ilu2.webp";
            }
            const weatherHTML = `
    <div class="row d-flex justify-content-center py-5">
         <div class="col-md-8 col-lg-6 col-xl-5">
            <div class="card text-body" style=" border-radius: 35px;">
              <div class="card-body p-4">
              <div class="d-flex">
          <h6 class="flex-grow-1">${cityName}</h6>
          <h6>${dateStr}</h6>
        </div>
        <div class="d-flex flex-column text-center mt-5 mb-4">
          <h6 class="display-4 mb-0 font-weight-bold"> ${weatherInfo.current.temp_c}°C </h6>
        </div>
        <div class="d-flex align-items-center">
          <div class="flex-grow-1" style="font-size: 1rem;">
            <div><i class="fas fa-wind fa-fw" style="color: #868B94;"></i> <span class="ms-1"> ${weatherInfo.current.wind_kph}km/h</span>
            </div>
            <div><i class="fas fa-tint fa-fw" style="color: #868B94;"></i> <span class="ms-1"> ${weatherInfo.current.humidity}%</span></div>
          </div>
          <div>
           <img src="${weatherImage}" width="100px">
          </div>
        </div>
    </div>
    </div>
    </div>
    </div>`;
            $(cityCard).find('.card-img-top').replaceWith(weatherHTML);
        }).fail(function (error) {
            console.error('Error fetching weather data:', error);
        });
    }

    $.get('http://localhost:8080/api/cities', function (cities) {
        const cityCardsContainer = $('#cityCards');

        $.each(cities, function (index, city) {
            const lastUpdatedTime = new Date(city.lastUpdated).getTime();
            const timeSinceLastUpdate = formatTimeSinceLastUpdate(lastUpdatedTime);

            const fullPictureUrl = `http://localhost:8080${city.pictureUrl}`;
            const cardHTML = `
                <div class="col-sm-4 mb-4">
                    <div class="card h-100">
<div class="image-container">
    <img src="${fullPictureUrl}" class="card-img-top city-picture" alt="${city.name}">
    <div class="info-overlay">Click to check out the weather</div>
</div>                        <div class="card-body">
                            <h5 class="card-title">${city.name}</h5>
                            <p class="card-text"><strong>Country:</strong> ${city.country}</p>
                            <p class="card-text"><strong>Language:</strong> ${city.language}</p>
                            <p class="card-text">${city.description}</p>
                        </div>
                        <div class="card-footer">
                            <small class="text-muted">Last updated ${timeSinceLastUpdate} ago</small>
                        </div>
                    </div>
                </div>
            `;
            cityCardsContainer.append(cardHTML);
        });

        $('.info-overlay').click(function () {
            const cityName = $(this).siblings('.city-picture').attr('alt');
            const cityCard = $(this).closest('.card');
            handleCityPictureClick(cityName, cityCard);
            $(this).hide();
        });
    }).fail(function (error) {
        console.error('Error fetching city data:', error);
    });
});

function formatTimeSinceLastUpdate(lastUpdatedTime) {
    const currentTime = new Date().getTime();
    const timeDifference = currentTime - lastUpdatedTime;

    // Calculate days, hours, and minutes
    const days = Math.floor(timeDifference / (1000 * 60 * 60 * 24));
    const remainingHours = Math.floor((timeDifference % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    const remainingMinutes = Math.round((timeDifference % (1000 * 60 * 60)) / (1000 * 60));

    let timeSinceLastUpdate = '';
    if (days > 0) {
        timeSinceLastUpdate += `${days} days `;
    }
    if (remainingHours > 0) {
        timeSinceLastUpdate += `${remainingHours} hours `;
    }
    if (days === 0 && remainingHours === 0) {
        timeSinceLastUpdate += `${remainingMinutes} minutes`;
    }

    return timeSinceLastUpdate;
}