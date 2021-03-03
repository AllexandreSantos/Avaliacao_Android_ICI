package br.org.curitiba.ici.avaliacao.databinding;
import br.org.curitiba.ici.avaliacao.R;
import br.org.curitiba.ici.avaliacao.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityGameBindingImpl extends ActivityGameBinding implements br.org.curitiba.ici.avaliacao.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.materialToolbar, 5);
        sViewsWithIds.put(R.id.opponentTextView, 6);
        sViewsWithIds.put(R.id.opponentImage, 7);
        sViewsWithIds.put(R.id.guideline, 8);
        sViewsWithIds.put(R.id.playerTextView, 9);
        sViewsWithIds.put(R.id.playerImage, 10);
        sViewsWithIds.put(R.id.radio_group, 11);
        sViewsWithIds.put(R.id.vsTextView, 12);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback3;
    @Nullable
    private final android.view.View.OnClickListener mCallback4;
    @Nullable
    private final android.view.View.OnClickListener mCallback1;
    @Nullable
    private final android.view.View.OnClickListener mCallback2;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityGameBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }
    private ActivityGameBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[4]
            , (androidx.constraintlayout.widget.Guideline) bindings[8]
            , (com.google.android.material.appbar.MaterialToolbar) bindings[5]
            , (android.widget.ImageView) bindings[7]
            , (android.widget.TextView) bindings[6]
            , (android.widget.ImageView) bindings[10]
            , (android.widget.TextView) bindings[9]
            , (android.widget.RadioGroup) bindings[11]
            , (android.widget.RadioButton) bindings[2]
            , (android.widget.RadioButton) bindings[1]
            , (android.widget.RadioButton) bindings[3]
            , (android.widget.TextView) bindings[12]
            );
        this.button.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.radioPaper.setTag(null);
        this.radioRock.setTag(null);
        this.radioScissor.setTag(null);
        setRootTag(root);
        // listeners
        mCallback3 = new br.org.curitiba.ici.avaliacao.generated.callback.OnClickListener(this, 3);
        mCallback4 = new br.org.curitiba.ici.avaliacao.generated.callback.OnClickListener(this, 4);
        mCallback1 = new br.org.curitiba.ici.avaliacao.generated.callback.OnClickListener(this, 1);
        mCallback2 = new br.org.curitiba.ici.avaliacao.generated.callback.OnClickListener(this, 2);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.viewModel == variableId) {
            setViewModel((br.org.curitiba.ici.avaliacao.game.GameViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable br.org.curitiba.ici.avaliacao.game.GameViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        br.org.curitiba.ici.avaliacao.game.GameViewModel viewModel = mViewModel;
        // batch finished
        if ((dirtyFlags & 0x2L) != 0) {
            // api target 1

            this.button.setOnClickListener(mCallback4);
            this.radioPaper.setOnClickListener(mCallback2);
            this.radioRock.setOnClickListener(mCallback1);
            this.radioScissor.setOnClickListener(mCallback3);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        switch(sourceId) {
            case 3: {
                // localize variables for thread safety
                // viewModel
                br.org.curitiba.ici.avaliacao.game.GameViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {




                    viewModel.setPlayerWeapon(br.org.curitiba.ici.avaliacao.game.entities.Weapon.SCISSOR);
                }
                break;
            }
            case 4: {
                // localize variables for thread safety
                // viewModel
                br.org.curitiba.ici.avaliacao.game.GameViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {


                    viewModel.play();
                }
                break;
            }
            case 1: {
                // localize variables for thread safety
                // viewModel
                br.org.curitiba.ici.avaliacao.game.GameViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {




                    viewModel.setPlayerWeapon(br.org.curitiba.ici.avaliacao.game.entities.Weapon.ROCK);
                }
                break;
            }
            case 2: {
                // localize variables for thread safety
                // viewModel
                br.org.curitiba.ici.avaliacao.game.GameViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {




                    viewModel.setPlayerWeapon(br.org.curitiba.ici.avaliacao.game.entities.Weapon.PAPER);
                }
                break;
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}