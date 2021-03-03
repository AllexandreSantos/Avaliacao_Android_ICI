package br.org.curitiba.ici.avaliacao.statistics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import java.util.Objects;

import br.org.curitiba.ici.avaliacao.R;
import br.org.curitiba.ici.avaliacao.databinding.ActivityStatisticsBinding;

public class StatisticsActivity extends AppCompatActivity {

    ActivityStatisticsBinding binding;
    StatisticsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_statistics);

        viewModel = ViewModelProviders.of(this).get(StatisticsViewModel.class);

        binding.setViewModel(viewModel);

        initViews();

        observeViewModel();
    }

    private void initViews() {
        setSupportActionBar(binding.materialToolbar);
        setTitle(R.string.statistics);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

    }

    private void observeViewModel() {
        viewModel.gameTotalsLiveData.observe(this, gameTotals -> {
            if (gameTotals != null){
                binding.totalGamesNumber.setText(String.valueOf(gameTotals.totalGames));
                binding.totalWinsNumber.setText(String.valueOf(gameTotals.totalWins));
                binding.totalLossesNumber.setText(String.valueOf(gameTotals.totalLosses));
                binding.totalDrawsNumber.setText(String.valueOf(gameTotals.totalDraws));
            }
        });

        viewModel.winRatio.observe(this, winRatio -> {
            if (winRatio != null) {
                binding.winRatioNumber.setText(String.valueOf(winRatio));
            }
        });
    }

}